/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018 Andres Almiray.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kordamp.gradle.jdktools

import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.JavaBasePlugin

/**
 * @author Andres Almiray
 */
@CompileStatic
class JDeprScanPlugin implements Plugin<Project> {
    Project project

    void apply(Project project) {
        this.project = project

        project.plugins.apply(JavaBasePlugin)

        project.tasks.findByName('check').dependsOn << project.task('jdeprScanReport',
            type: JDeprScanReportTask,
            group: BasePlugin.BUILD_GROUP,
            dependsOn: 'classes',
            description: 'Generate a jdeprscan report on project classes and dependencies')
    }
}
